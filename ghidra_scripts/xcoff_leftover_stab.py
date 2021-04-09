#Recover function names from intra-function stab info
#@author silentsignal 
#@category Analysis
#@keybinding 
#@menupath 
#@toolbar 

import struct
from ghidra.program.model.symbol import SourceType

f=getFirstFunction()

while f is not None:
    print(f)
    nextF=getFunctionAfter(f)
    if nextF is None:
        break
    diff=nextF.getBody().getMaxAddress().getOffset()-f.getBody().getMaxAddress().getOffset()    
    if diff>4:
        print("Found gap")
        for d in range(0,diff-2):
            length=struct.unpack(">H",getBytes(f.getBody().getMaxAddress().add(d),2))[0]
            if length>0 and length+d < diff:
                print("Found possible length @ 0x%x -> 0x%x" % (d,length))
                isStr=True
                myStr=[]
                for c in range(d+2, d+2+length):
                    b=getByte(f.getBody().getMaxAddress().add(c))
                    if b>=0x20 and b<0x7f:
                        myStr.append(chr(b))
                    else:
                        isStr=False
                        break
                if isStr and len(myStr)>3:
                    name="".join(myStr).strip()
                    print("Setting %s" % (name))
                    f.setName(name, SourceType.ANALYSIS)
                    break
    f=nextF
