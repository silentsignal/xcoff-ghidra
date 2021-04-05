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
import ghidra.app.util.bin.format.xcoff.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.*;

import ghidra.program.flatapi.FlatProgramAPI;
import ghidra.app.util.MemoryBlockUtils;
import ghidra.app.util.Option;
import ghidra.app.util.bin.BinaryReader;
import ghidra.app.util.bin.ByteProvider;
import ghidra.app.util.bin.format.xcoff.XCoffFileHeaderMagic;
import ghidra.app.util.importer.MessageLog;
import ghidra.app.util.opinion.AbstractLibrarySupportLoader;
import ghidra.app.util.opinion.LoadSpec;
import ghidra.app.util.opinion.QueryOpinionService;
import ghidra.app.util.opinion.QueryResult;
import ghidra.framework.model.DomainObject;
import ghidra.program.model.address.Address;
import ghidra.program.model.address.AddressFactory;
import ghidra.program.model.address.AddressOverflowException;
import ghidra.program.model.data.DataUtilities;
import ghidra.program.model.data.Pointer;
import ghidra.program.model.data.PointerDataType;
import ghidra.program.model.lang.LanguageCompilerSpecPair;
import ghidra.program.model.listing.Data;
import ghidra.program.model.listing.Program;
import ghidra.program.model.symbol.Reference;
import ghidra.util.exception.CancelledException;
import ghidra.util.task.TaskMonitor;
import io.kaitai.struct.ByteBufferKaitaiStream;
import xcoff.Xcoff32.SectionHeader;

/**
 * TODO: Provide class-level documentation that describes what this loader does.
 */
public class XCOFFLoader extends AbstractLibrarySupportLoader {

	@Override
	public String getName() {
		return "XCOFF loader";
	}

	@Override
	public Collection<LoadSpec> findSupportedLoadSpecs(ByteProvider provider) throws IOException {
		List<LoadSpec> loadSpecs = new ArrayList<>();

		// TODO: Examine the bytes in 'provider' to determine if this loader can load it.  If it 
		// can load it, return the appropriate load specifications.
		BinaryReader reader = new BinaryReader(provider, false/*always big endian*/);
		if (XCoffFileHeaderMagic.isMatch(reader.peekNextShort())) {
			loadSpecs.add(new LoadSpec(this, 0x400000, new LanguageCompilerSpecPair("PowerPC:BE:32:default", "default"),true));
		}
		return loadSpecs;
	}

	@Override
	protected void load(ByteProvider provider, LoadSpec loadSpec, List<Option> options,
			Program program, TaskMonitor monitor, MessageLog log)
			throws CancelledException, IOException {
		
		Xcoff32 xcoff = new Xcoff32(new ByteBufferKaitaiStream(provider.readBytes(0, provider.length())));
		AddressFactory af = program.getAddressFactory();
		for (SectionHeader sectionHeader : xcoff.sectionHeaders()) {
			Address start = af.getDefaultAddressSpace().getAddress( sectionHeader.sVaddr() );
			try {
				if (sectionHeader.sFlags() != 0x80) {
					MemoryBlockUtils.createInitializedBlock(program, false, sectionHeader.sName(), start,new ByteArrayInputStream(sectionHeader.body()), sectionHeader.sSize(), "", sectionHeader.sName(), true, true, true, log, monitor);
				}else {
					MemoryBlockUtils.createUninitializedBlock(program, false, sectionHeader.sName(), start, sectionHeader.sSize(), "", "", true, true, true, log);
				}
			} catch (AddressOverflowException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Address entry_struct_address = af.getDefaultAddressSpace().getAddress(xcoff.auxiliaryHeader().oEntry());
		FlatProgramAPI api= new FlatProgramAPI(program);
		try {
			api.createData(entry_struct_address,new PointerDataType());
			for (Reference ref:api.getReferencesFrom(entry_struct_address)) {
				api.createFunction(ref.getToAddress(),"___start");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		// TODO: Load the bytes from 'provider' into the 'program'.
	}

	@Override
	public List<Option> getDefaultOptions(ByteProvider provider, LoadSpec loadSpec,
			DomainObject domainObject, boolean isLoadIntoProgram) {
		List<Option> list =
			super.getDefaultOptions(provider, loadSpec, domainObject, isLoadIntoProgram);

		// TODO: If this loader has custom options, add them to 'list'
		list.add(new Option("Option name goes here", "Default option value goes here"));

		return list;
	}

	@Override
	public String validateOptions(ByteProvider provider, LoadSpec loadSpec, List<Option> options, Program program) {

		// TODO: If this loader has custom options, validate them here.  Not all options require
		// validation.

		return super.validateOptions(provider, loadSpec, options, program);
	}
}