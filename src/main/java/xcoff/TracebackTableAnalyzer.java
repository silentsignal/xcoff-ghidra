/* ###
 * IP: GHIDRA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package xcoff;

import java.util.Arrays;

import ghidra.app.services.AbstractAnalyzer;
import ghidra.app.services.AnalyzerType;
import ghidra.app.util.importer.MessageLog;
import ghidra.framework.options.Options;
import ghidra.program.model.address.AddressOutOfBoundsException;
import ghidra.program.model.address.AddressSetView;
import ghidra.program.model.lang.Language;
import ghidra.program.model.lang.Processor;
import ghidra.program.model.listing.FunctionIterator;
import ghidra.program.model.listing.Program;
import ghidra.program.model.mem.Memory;
import ghidra.program.model.mem.MemoryAccessException;
import ghidra.program.model.symbol.SourceType;
import ghidra.util.exception.CancelledException;
import ghidra.util.exception.DuplicateNameException;
import ghidra.util.exception.InvalidInputException;
import ghidra.util.task.TaskMonitor;
import io.kaitai.struct.ByteBufferKaitaiStream;
import io.kaitai.struct.KaitaiStream.ValidationNotEqualError;
import ghidra.program.model.listing.Function;

/**
 Special thanks to ohmantics:
 https://www.reddit.com/r/ghidra/comments/mldyv5/adding_xcoff_support_to_ghidra_with_kaitai_struct/gtm5s8s
 */
public class TracebackTableAnalyzer extends AbstractAnalyzer {

	public TracebackTableAnalyzer() {
		super("Traceback Table Analyzer", "Parses PowerPC Traceback table information", AnalyzerType.FUNCTION_ANALYZER);
	}

	@Override
	public boolean getDefaultEnablement(Program program) {

		return true;
	}

	@Override
	public boolean canAnalyze(Program program) {
		Language lang=program.getLanguage();
		Processor proc=lang.getProcessor();
		boolean be=lang.isBigEndian();
		if (lang.isBigEndian() && proc.equals(Processor.findOrPossiblyCreateProcessor("PowerPC"))) {
			return true;
		}

		return false;
	}

	@Override
	public void registerOptions(Options options, Program program) {

		// TODO: If this analyzer has custom options, register them here

		//options.registerOption("Option name goes here", false, null,
		//	"Option description goes here");
	}

	@Override
	public boolean added(Program program, AddressSetView set, TaskMonitor monitor, MessageLog log)
			throws CancelledException {

		FunctionIterator functions = program.getListing().getFunctions(set, true);
		while(functions.hasNext()) {
			Function f=functions.next();
			byte[] bytes=new byte[128]; // Ought to be enough for any function name... 
			try {
				program.getMemory().getBytes(f.getBody().getMaxAddress().add(1), bytes);
				try {
					TracebackTable tb=new TracebackTable(new ByteBufferKaitaiStream(bytes));
					if(tb.mandatoryFields().namePresent()) {
						f.setName(tb.optionalFields().nameStruct().name(),SourceType.ANALYSIS);
					}
				}catch(ValidationNotEqualError vne) {
					
				}catch(InvalidInputException iie) {
					
				}catch(DuplicateNameException dne) {
					
				}
			} catch (MemoryAccessException | AddressOutOfBoundsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return false;
	}
}
